import { getToken } from "next-auth/jwt";
import { redirect } from "next/navigation";

import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

async function getHistory(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/termini`;

	console.log(url);
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

	throw new Error("Failed to fetch history. Status: " + res.status);
}

export default async function History() {
	const session = await getServerSession(authOptions);
	if (session) {
		try {
			const history = await getHistory(
				session.user.preferred_username,
				session.token
			);
			return (
				<main className="flex flex-col p-8">
					<h1 className="text-4xl font-bold">Zgodovina</h1>
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
