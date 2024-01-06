"use client";

import { useState } from "react";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";

export default function LoginForm() {
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [error, setError] = useState("");

	const router = useRouter();

	const handleSubmit = async (e) => {
		e.preventDefault();

		try {
			const res = await signIn("credentials", {
				username,
				password,
				redirect: false,
			});

			if (res.error) {
				setError("Napačni podatki!");
				return;
			}

			// router.replace("appointments");
		} catch (err) {
			console.log(err);
		}
	};

	return (
		<div className="h-screen grid place-items-center">
			<div className="shadow-lg p-5 border-t-4 border-lime-800 rounded-lg">
				<h1 className="text-xl font-bold my-4 text-neutral-950">
					Prijava
				</h1>
				<form
					onSubmit={handleSubmit}
					className="flex flex-col gap-3 text-neutral-950"
				>
					<input
						onChange={(e) => setUsername(e.target.value)}
						type="text"
						placeholder="Uporabniško ime"
						className="py-2 px-4"
					/>
					<input
						onChange={(e) => setPassword(e.target.value)}
						type="password"
						placeholder="Geslo"
						className="py-2 px-4"
					/>
					<button className="btn px-6 py-2 cursor-pointer bg-lime-800 font-bold text-sm text-neutral-50 w-fit">
						Prijava
					</button>
				</form>
				{error && (
					<div className="bg-red-800 text-neutral-950 w-fit text-sm py-1 px-3 rounded-md mt-2">
						{error}
					</div>
				)}
			</div>
		</div>
	);
}
