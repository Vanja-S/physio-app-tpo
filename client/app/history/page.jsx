import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Appointment from "@/components/Appointment";
import Plan from "@/components/Plan";

async function getAppointments(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	return res;
}

async function getPlans(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/fizioplani`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});
	return res;
}

export default async function History() {
	const session = await getServerSession(authOptions);
	if (session) {
		const appointments = await getAppointments(
			session.user.preferred_username,
			session.token
		).then(async (response) => {
			if (!response.ok) {
				return response.json().catch(() => {
					if (response.status == "401") {
						// redirect("/login");
					}
				});
			}
			return await response.json();
		});

		const plans = await getPlans(
			session.user.preferred_username,
			session.token
		).then(async (response) => {
			if (!response.ok) {
				return response.json().catch(() => {
					if (response.status == "401") {
						// redirect("/login");
					}
				});
			}
			return await response.json();
		});

		return (
			<main className="flex flex-col p-8 h-screen">
				<h1 className="text-4xl font-bold mb-4">Zgodovina</h1>
				<div className="flex flex-col md:flex-row text-platinum gap-8 h-full">
					<div className="w-1/2 h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
						<h1 className="text-2xl font-bold">
							Zgodovina terminov
						</h1>
						{appointments &&
							appointments.map((appointment) => {
								<Appointment
									appointment={appointment}
								></Appointment>;
							})}
						{!appointments && (
							<p className="text-lg">
								Nimate preteklih terminov.
							</p>
						)}
					</div>
					<div className="w-1/2 h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
						<h1 className="text-2xl font-bold">Zgodovina planov</h1>

						{plans &&
							plans.map((plan) => {
								<Plan plan={plan}></Plan>;
							})}
						{!plans && (
							<p className="text-lg">
								Nimate preteklih fizioplanov.
							</p>
						)}
					</div>
				</div>
			</main>
		);
	} else {
		redirect("/login");
	}
}
