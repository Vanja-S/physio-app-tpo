import { redirect } from "next/navigation";
import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Appointment from "@/components/Appointment";
import Plan from "@/components/Plan.jsx";
import CancelAppointment from "@/components/CancelAppointment";

async function getNextAppointment(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini/next`;
	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		const data = await res.json();
		return data;
	}

	return null;
}

export default async function Home() {
	const session = await getServerSession(authOptions);

	if (session) {
		try {
			const appointment = await getNextAppointment(
				session.user.preferred_username,
				session.token
			);

			return (
				<main className="h-screen text-jet flex flex-col p-8">
					<h1 className="text-4xl font-bold mb-4">
						Pozdravljen, {session.user.preferred_username}👋
					</h1>
					<div className="flex w-full h-full items-start gap-8">
						<div className="h-full w-1/2 rounded-md shadow-lg p-4 bg-carribeanCurrent/40 text-white">
							<h1 className="text-2xl font-bold">Fizioplan</h1>
							<Plan
								username={session.user.preferred_username}
								token={session.token}
							></Plan>
						</div>
						<div className="h-fit w-fit rounded-md p-4 bg-carribeanCurrent/40 text-white">
							<h1 className="text-2xl font-bold">
								Naslednji termin
							</h1>
							{appointment && (
								<div>
									<Appointment
										appointment={appointment}
									></Appointment>
									<div className="flex w-full justify-center mt-2">
										<CancelAppointment
											id={appointment.terminId}
											token={session.token}
										></CancelAppointment>
									</div>
								</div>
							)}
							{!appointment && (
								<p className="text-lg">
									Nimate rezerviranega termina.
								</p>
							)}
						</div>
					</div>
				</main>
			);
		} catch (e) {
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Nadzorna plošča</h1>
					<p className="text-lg">
						Sorry, an error happened. Check the server logs.
					</p>
				</main>
			);
		}
	} else {
		redirect("/login");
	}
}
