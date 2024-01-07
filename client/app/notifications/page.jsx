import { getServerSession } from "next-auth";
import { getAccessToken } from "@/utils/sessionTokenAccessor";
import { redirect } from "next/navigation";
import { authOptions } from "../api/auth/[...nextauth]/route";

async function getNotifications() {
	const url = `${process.env.BACKEND_URL}/api/v1/notifications`;

	let accessToken = await getAccessToken();

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer" + accessToken,
		},
	});

	if (res.ok) {
		const data = await res.json();
		return data;
	}

	throw new Error("Failed to fetch notifications. Status: " + res.status);
}

export default async function Notifications() {
	const session = await getServerSession(authOptions);
	if (session) {
		try {
			const notifications = await getNotifications();
			return (
				<main>
					<h1>Appointment</h1>
				</main>
			);
		} catch (e) {
			console.error(e);
			return (
				<main>
					<h1 className="text-4xl text-center">Notifications</h1>
					<p className="text-red-600 text-center text-lg">
						Sorry, an error happened. Check the server logs.
					</p>
				</main>
			);
		}
	} else {
		redirect("/login");
	}
}