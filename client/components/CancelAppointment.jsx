"use client";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function CancelAppointment({ id, token }) {
	const router = useRouter();

	const [success, setSuccess] = useState(false);
	const [error, setError] = useState(false);

	const handleClick = async (e) => {
		e.preventDefault();
		const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/termini/termini/${id}/cancel`;

		await fetch(url, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: "Bearer " + token,
			},
		}).then((response) => {
			if (response.ok) {
				setSuccess(true);
				setTimeout(() => {
					router.refresh();
					setSuccess(false);
				}, 1000);
			} else {
				setError(true);
				setTimeout(() => {
					router.refresh();
					setError(false);
				}, 1000);
			}
		});
	};

	return (
		<div>
			<button
				className="bg-indigoDye rounded-md w-fit p-1 text-xs"
				onClick={handleClick}
			>
				Prekliči pregled
			</button>
			{success && (
				<div className="absolute bottom-4 right-4 bg-[#00FF00]/50 p-4 w-52 h-fit text-center text-md">
					Preklicali ste termin.
				</div>
			)}
			{error && (
				<div className="absolute bottom-4 right-4 bg-[#FF0000]/50 p-4 w-52 h-fit text-center text-md">
					Napaka v omrežju.
				</div>
			)}
		</div>
	);
}
