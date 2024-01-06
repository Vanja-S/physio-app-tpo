import { getServerSession } from "next-auth";
import { redirect } from "next/navigation";
import { authOptions } from "./api/auth/[...nextauth]/route";
import LoginForm from "@/components/loginForm";

export default async function Home() {
	const session = await getServerSession(authOptions);

	if (session) redirect("/dashboard");

	return (
		<main className="bg-neutral-50">
			<LoginForm />
		</main>
	);
}
