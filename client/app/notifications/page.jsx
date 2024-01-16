import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Notification from "@/components/Notification";

async function getNotifications(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/obvestila`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	return res;
}

export default async function Notifications() {
	const session = await getServerSession(authOptions);
	if (session) {
		const notifications = await getNotifications(
			session.user.preferred_username,
			session.token
		).then(async (response) => {
			if (!response.ok) {
				return null;
			}
			return await response.json();
		});

		return (
			<main className="flex flex-col p-8 h-screen">
				<h1 className="text-4xl font-bold mb-4">Obvestila</h1>
				<div className="w-full h-full rounded-lg shadow-lg p-4 bg-carribeanCurrent/40 text-white">
					<h1 className="text-2xl font-bold">Obvestila</h1>
					{notifications &&
						notifications.map((notification) => {
							<Notification
								notification={notification}
							></Notification>;
						})}
					{!notifications && (
						<p className="text-lg">Nimate obvestil.</p>
					)}
				</div>
			</main>
		);
	} else {
		redirect("/login");
	}
}
