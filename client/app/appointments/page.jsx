import { getToken } from "next-auth/jwt";
import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

async function getFreeAppointments(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		const data = await res.json();
		console.log(data);
		return data;
	}

	throw new Error("Failed to fetch appointments. Status: " + res.status);
}

export default async function Appointments() {
	const session = await getServerSession(authOptions);

	if (session) {
		try {
			const appointments = await getFreeAppointments(
				session.user.preferred_username,
				session.token
			);
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Termini</h1>
					{appointments &&
						appointments.array.forEach((appointment) => {
							console.log(appointment);
						})}
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
