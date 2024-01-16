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
	return res;
}

async function getCurrentPlan(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/fizioplan`;
	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});
	return res;
}

export default async function Home() {
	const plans = [
		{
			id: 1,
			naslov: "Naslov1",
			poskodba: "Poskodba1",
			trajanjeOd: 1705618800,
			trajanjeDo: 1713477600,
			opis: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in",
		},
		{
			id: 2,
			naslov: "Naslov2",
			poskodba: "Poskodba2",
			trajanjeOd: 1708556400,
			trajanjeDo: 1711062000,
			opis: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in",
		},
		{
			id: 3,
			naslov: "Naslov3",
			poskodba: "Poskodba3",
			trajanjeOd: 1704582000,
			trajanjeDo: 1707260400,
			opis: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in",
		},
	];

	const session = await getServerSession(authOptions);

	if (session) {
		const appointment = await getNextAppointment(
			session.user.preferred_username,
			session.token
		).then(async (response) => {
			if (!response.ok) {
				return null;
			}
			return await response.json();
		});

		const plan = plans.at(1);

		return (
			<main className="h-screen text-jet flex flex-col p-8">
				<h1 className="text-4xl font-bold mb-4">Nadzorna plošča</h1>
				<div className="flex w-full h-full items-start gap-8">
					<div className="h-full w-1/3 rounded-md p-4 bg-carribeanCurrent/40 text-white">
						<h1 className="text-2xl font-bold">Naslednji termin</h1>
						{appointment && (
							<div>
								<Appointment
									appointment={appointment}
								></Appointment>
								<div className="flex w-full justify-center pt-2 pb-4">
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
					<div className="h-full w-full rounded-md shadow-lg p-4 bg-carribeanCurrent/40 text-white">
						<h1 className="text-2xl font-bold">Fizioplan</h1>
						{plan && <Plan plan={plan}></Plan>}
						{!plan && (
							<p className="text-lg">Trenutno nimate plana.</p>
						)}
					</div>
				</div>
			</main>
		);
	} else {
		redirect("/login");
	}
}
