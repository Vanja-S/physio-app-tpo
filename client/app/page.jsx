import { redirect } from "next/navigation";
import { getServerSession } from "next-auth";

import Appointments from "./appointments/page";

export default async function Home() {
	const session = await getServerSession();
	if (session) {
		return (
			<main className="h-screen text-jet flex flex-col p-8">
				<h1 className="text-4xl font-bold mb-8">
					Pozdravljen, {session.user?.name}👋
				</h1>
				<div className="h-fit w-96 bg-platinum/50 rounded-lg"></div>
				<div className="h-fit w-96 bg-platinum/50 rounded-lg"></div>
			</main>
		);
	} else {
		redirect("/login");
	}
}
