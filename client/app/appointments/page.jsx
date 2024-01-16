import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import FreeAppointments from "@/components/FreeAppointments";
import Appointment from "@/components/Appointment";
import CancelAppointment from "@/components/CancelAppointment";

async function getNextAppointment(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini/next`;
	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	return res;
}

export default async function Appointments() {
	const session = await getServerSession(authOptions);

	if (session) {
		let nextAppointment = await getNextAppointment(
			session.user.preferred_username,
			session.token
		).then(async (response) => {
			if (!response.ok) {
				return null;
			}
			return await response.json();
		});

		return (
			<main className="flex flex-col p-8 h-screen w-full">
				<h1 className="text-4xl font-bold mb-4">Termini</h1>
				<div className="flex h-full gap-8">
					<div className="w-1/3 h-full bg-carribeanCurrent/40 p-4 rounded-lg text-white">
						<h1 className="text-2xl font-bold">Naslednji termin</h1>
						{nextAppointment && (
							<div>
								<Appointment
									appointment={nextAppointment}
								></Appointment>
								<div className="flex w-full justify-center mt-2">
									<CancelAppointment
										id={nextAppointment.terminId}
										token={session.token}
									></CancelAppointment>
								</div>
							</div>
						)}
						{!nextAppointment && (
							<p className="text-lg">
								Nimate rezerviranega termina.
							</p>
						)}
					</div>
					<div className="flex-col h-full w-full bg-carribeanCurrent/40 p-4 rounded-lg">
						<FreeAppointments session={session}></FreeAppointments>
					</div>
				</div>
			</main>
		);
	} else {
		redirect("/login");
	}
}
