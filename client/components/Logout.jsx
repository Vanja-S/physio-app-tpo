"use client";

import { signOut } from "next-auth/react";
import { useRouter } from "next/navigation";

export default function Logout() {
	const handleSubmit = (e) => {
		e.preventDefault();
		// await signOut("credentials");
	};
}
