import Credentials from "next-auth/providers/credentials";
import { authenticate } from "@/app/services/AuthService";

import { getServerSession } from "next-auth";

const authOptions = {
	providers: [
		Credentials({
			name: "credentials",
			credentials: {
				username: { label: "Username", type: "text" },
				password: { label: "Password", type: "password" },
			},
			async authorize(credentials) {
				if (!credentials.username || !credentials.password) return null;
				const user = await authenticate(
					credentials.username,
					credentials.password
				);
				return user;
			},
		}),
	],
	session: {
		strategy: "jwt",
	},
	secret: process.env.NEXT_PUBLIC_NEXTAUTH_SECRET,
	callbacks: {
		async jwt({ token, user }) {
			if (user) {
				return {
					...token,
					accessToken: user.access_token,
					expiresIn: user.expires_in,
					refreshToken: user.refresh_token,
					refreshExpiresIn: user.refresh_expires_in,
					sessionState: user.session_state,
				};
			}

			return token;
		},
		async session({ session, token }) {
			session.token = token.accessToken;
			const claim = atob(token.accessToken.split(".").at(1));
			session.user = JSON.parse(claim);
			return session;
		},
	},
};

function auth(...args) {
	// <-- use this function to access the jwt from React components
	return getServerSession(...args, authOptions);
}

export { authOptions, auth };
