"use client";
import { useRouter } from "next/navigation";

export default function CancelAppointment({ id, token }) {
	const router = useRouter();

	const handleClick = async () => {
		const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/termini/termini/${id}/cancel`;

		await fetch(url, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: "Bearer " + token,
			},
		}).then(() => {
			setTimeout(() => {
				router.push("/");
				router.refresh();
			}, 1000);
		});
	};

	return (
		<button
			className="border-2 border-indigoDye rounded-md w-fit p-1 text-xs"
			onClick={handleClick}
		>
			Prekliči pregled
		</button>
	);
}
