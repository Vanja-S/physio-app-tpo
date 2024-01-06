import { getServerSession } from "next-auth";
import { getAccessToken } from "@/utils/sessionTokenAccessor";
import { redirect } from "next/navigation";
import { authOptions } from "../api/auth/[...nextauth]/route";

async function getUser() {
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

	throw new Error("Failed to fetch user data. Status: " + res.status);
}

export default async function Settings() {
	const session = await getServerSession(authOptions);
	if (session) {
		try {
			const user = await getUser();
			return (
				<main>
					<h1>{user}</h1>
				</main>
			);
		} catch (e) {
			console.error(e);
			return (
				<main>
					<h1 className="text-4xl text-center">User settings</h1>
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
