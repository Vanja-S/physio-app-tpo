import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Appointment from "@/components/Appointment";
import Plan from "@/components/Plan";

async function getHistory(username, token) {
	let apps = [];
	let plans = [];

	let url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini`;

	let res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		apps = await res.json();
	} else {
		throw new Error("Failed to fetch history. Status: " + res.status);
	}

	url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/fizioplani`;

	res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		plans = await res.json();
	} else {
		throw new Error("Failed to fetch history. Status: " + res.status);
	}

	return { apps, plans };
}

export default async function History() {
	const session = await getServerSession(authOptions);
	if (session) {
		try {
			const { apps, plans } = await getHistory(
				session.user.preferred_username,
				session.token
			);
			return (
				<main className="flex flex-col p-8 h-screen">
					<h1 className="text-4xl font-bold mb-4">Zgodovina</h1>
					<div className="flex flex-col md:flex-row text-platinum gap-8 h-full">
						<div className="w-1/3 h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
							<h1 className="text-2xl font-bold">
								Zgodovina terminov
							</h1>
							{apps &&
								apps.length != 0 &&
								apps.map((appointment) => {
									<Appointment
										appointment={appointment}
									></Appointment>;
								})}
							{!apps ||
								(apps.length == 0 && (
									<p className="text-lg">
										Nimate preteklih terminov.
									</p>
								))}
						</div>
						<div className="w-1/3 h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
							<h1 className="text-2xl font-bold">
								Zgodovina planov
							</h1>
							<pre>
								{plans &&
									plans.length != 0 &&
									plans.map((plan) => {
										<Plan plan={plan}></Plan>;
									})}
							</pre>
							{!plans ||
								(plans.length == 0 && (
									<p className="text-lg">
										Nimate preteklih fizioplanov.
									</p>
								))}
						</div>
					</div>
				</main>
			);
		} catch (e) {
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Zgodovina</h1>
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
