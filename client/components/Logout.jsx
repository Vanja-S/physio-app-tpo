"use client";

import { signOut } from "next-auth/react";
import { useRouter } from "next/navigation";
// import { logout } from "@/app/services/AuthService";
import { ArrowLeftEndOnRectangleIcon } from "@heroicons/react/20/solid";

export default function Logout() {
	const router = useRouter();
	return (
		<button
			onClick={() => {
				signOut({ callbackUrl: "/login" });
				// logout();
				router.refresh();
			}}
			className="btn p-1 md:p-2 cursor-pointer bg-platinum font-bold text-sm text-indigoDye rounded-full w-fit"
		>
			<ArrowLeftEndOnRectangleIcon className="w-8 h-8"></ArrowLeftEndOnRectangleIcon>
		</button>
	);
}
