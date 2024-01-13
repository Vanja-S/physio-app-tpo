import { getToken } from "next-auth/jwt";
import { withAuth } from "next-auth/middleware";

// export { default } from "next-auth/middleware";

export default withAuth(async function middleware(req) {
	const token = await getToken({ req });
	const isAuthenticated = !!token;

	if (req.nextUrl.pathname.startsWith("/login")) {
		if (isAuthenticated) {
			return NextResponse.redirect(new URL("/", req.url));
		}
	}
});

export const config = { matcher: "/" };
