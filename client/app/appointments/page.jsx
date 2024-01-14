import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Koledar from "@/components/Koledar";
import Appointment from "@/components/Appointment";
import CancelAppointment from "@/components/CancelAppointment";

async function getFreeAppointments(username, token, date) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}termini/${username}/available?izbranDatum=${date}`;

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

	return [];
}

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

function formatDate(date) {
	const year = date.toLocaleString("default", { year: "numeric" });
	const month = date.toLocaleString("default", {
		month: "2-digit",
	});
	const day = date.toLocaleString("default", { day: "2-digit" });

	return [year, month, day].join("-");
}

export default async function Appointments() {
	const session = await getServerSession(authOptions);

	if (session) {
		try {
			let freeAppointments = await getFreeAppointments(
				session.user.preferred_username,
				session.token,
				formatDate(new Date())
			);

			let nextAppointment = await getNextAppointment(
				session.user.preferred_username,
				session.token
			);

			return (
				<main className="flex flex-col p-8 h-screen w-full">
					<h1 className="text-4xl font-bold mb-4">Termini</h1>
					<div className="flex h-full gap-8">
						<div className="w-fit h-fit bg-carribeanCurrent/40 p-4 rounded-lg text-white">
							<h1 className="text-2xl font-bold">
								Naslednji termin
							</h1>
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
						<div className="flex-col w-full h-full space-y-8 pb-8">
							<div className="h-2/3 bg-carribeanCurrent/40 p-4 rounded-lg text-white">
								<Koledar></Koledar>
							</div>
							<div className="h-1/3 bg-carribeanCurrent/40 p-4 rounded-lg text-white">
								{freeAppointments &&
									freeAppointments.length != 0 &&
									freeAppointments.map((appointment) => (
										<Appointment
											key={appointment.id}
											appointment={appointment}
										></Appointment>
									))}
								{!freeAppointments ||
									(freeAppointments.length == 0 && (
										<p className="text-lg">
											Na ta datum ni prostih terminov.
										</p>
									))}
							</div>
						</div>
					</div>
				</main>
			);
		} catch (e) {
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Termini</h1>
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
