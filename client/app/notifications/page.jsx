import { getToken } from "next-auth/jwt";
import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";
import { signOut } from "next-auth/react";

import Notification from "@/components/Notification";

async function getNotifications(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/obvestila`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		const data = await res.json().catch((e) => console.log(e));
		return data;
	}

	throw new Error("Failed to fetch notifications. Status: " + res.status);
}

export default async function Notifications() {
	const session = await getServerSession(authOptions);
	if (session) {
		try {
			const notifications =
				(await getNotifications(
					session.user.preferred_username,
					session.token
				)) || [];

			return (
				<main className="flex flex-col p-8 h-screen">
					<h1 className="text-4xl font-bold mb-4">Obvestila</h1>
					<div className="w-1/3 h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
						<h1 className="text-2xl font-bold">Obvestila</h1>
						{notifications &&
							notifications.length != 0 &&
							notifications.map((notification) => {
								<Notification
									notification={notification}
								></Notification>;
							})}
						{!notifications ||
							(notifications.length == 0 && (
								<p className="text-lg">Nimate obvestil.</p>
							))}
					</div>
				</main>
			);
		} catch (e) {
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Obvestila</h1>
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
