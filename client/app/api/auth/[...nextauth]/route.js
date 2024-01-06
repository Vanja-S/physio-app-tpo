import NextAuth from "next-auth/next";
import CredentialsProvider from "next-auth/providers/credentials";
import axios from "axios";
import { jwt_decode } from "jwt-decode";

export const authOptions = {
	providers: [
		CredentialsProvider({
			name: "credentials",
			credentials: {
				username: { label: "Username", type: "text" },
				password: { label: "Password", type: "password" },
			},
			async authorize(credentials, req) {
				const payload = {
					grant_type: "password",
					username: username,
					password: password,
					client_id: "fizio",
				};

				const res = await fetch(process.env.KEYCLOAK_TOKEN, {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded",
					},
					data: JSON.stringify(payload),
				});

				const user = await res.json();
				if (!res.ok) {
					throw new Error(user.message);
				}

				if (user) {
					return user;
				}
				return null;
			},
		}),
	],
	secret: process.env.JWT_SECRET,
	pages: {
		signIn: "/login",
	},
	callbacks: {
		// async jwt({ token, account }) {
		// 	const nowTimeStamp = Math.floor(Date.now() / 1000);
		// 	console.log(account);
		// 	if (account) {
		// 		token.decoded = jwt_decode(account.access_token);
		// 		token.accessToken = account.access_token;
		// 		token.id_token = account.id_token;
		// 		token.expires_at = account.expires_at;
		// 		token.refresh_token = account.refresh_token;
		// 		return token;
		// 	} else if (nowTimeStamp < token.expires_at) {
		// 		return token;
		// 	} else {
		// 		console.log("Token has expired!");
		// 		// TODO
		// 		return token;
		// 	}
		// },
		// async session({ session, token }) {
		// 	session.access_token = encrypt(token.access_token);
		// 	session.id_token = encrypt(token.id_token);
		// 	session.roles = token.decoded.realm_access.roles;
		// 	return session;
		// },
	},
};

const handler = NextAuth(authOptions);

export { handler as GET, handler as POST };
