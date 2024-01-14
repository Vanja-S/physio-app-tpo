import { getToken } from "next-auth/jwt";
import { withAuth } from "next-auth/middleware";
import { signOut } from "next-auth/react";
import { NextResponse } from "next/server";

// export { default } from "next-auth/middleware";

export default withAuth(async function middleware(req) {
	const token = await getToken({ req });
	const isAuthenticated = !!token;

	// if (token.expiresIn < Date.now()) {
	// 	signOut({ callbackUrl: "/login" });
	// 	return;
	// }

	if (req.nextUrl.pathname.startsWith("/login")) {
		if (isAuthenticated) {
			return NextResponse.redirect(new URL("/", req.url));
		}
	}
});

export const config = {
	matcher: ["/", "/notifications", "/appointments", "/history"],
};
