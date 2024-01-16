"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

import { signIn, signOut } from "next-auth/react";

export default function LoginForm() {
	const [data, setData] = useState({ username: "", password: "" });
	const [error, setError] = useState("");
	const [success, setSuccess] = useState("");

	const router = useRouter();

	const handleSubmit = async (e) => {
		e.preventDefault();

		setError("");
		setSuccess("");

		const status = await signIn("credentials", {
			...data,
			redirect: false,
		});
		if (status?.ok) {
			setSuccess("Prijava je bila uspešna!");
			setTimeout(() => {
				router.push("/");
				router.refresh();
			}, 1000);
		} else {
			setError("Napaka! Preverite podatke.");
		}
	};

	return (
		<div className="h-screen flex flex-col justify-center md:ml-[-80px]">
			<h1 className="text-5xl font-bold my-4 text-indigoDye text-center">
				Physio<span className="text-white">App</span>
			</h1>
			<div className="shadow-lg p-5 rounded-lg text-jet">
				<form onSubmit={handleSubmit} className="flex flex-col gap-3">
					<input
						onChange={(e) =>
							setData({ ...data, username: e.target.value })
						}
						type="text"
						placeholder="Uporabniško ime"
						className="py-2 px-4"
					/>
					<input
						onChange={(e) =>
							setData({ ...data, password: e.target.value })
						}
						type="password"
						placeholder="Geslo"
						className="py-2 px-4"
					/>
					<button className="btn px-6 py-2 cursor-pointer bg-carribeanCurrent font-bold text-sm text-white w-fit rounded">
						Prijava
					</button>
				</form>
				{error && (
					<div className="bg-[#FF0000] text-white w-fit text-sm py-1 px-3 rounded-md mt-2">
						{error}
					</div>
				)}
				{success && (
					<div className="bg-[#00FF00] text-jet w-fit text-sm py-1 px-3 rounded-md mt-2">
						{success}
					</div>
				)}
			</div>
		</div>
	);
}
