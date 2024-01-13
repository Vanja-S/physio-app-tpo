export async function authenticate(username, password) {
	const res = await fetch(process.env.NEXT_PUBLIC_KEYCLOAK_TOKEN, {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
		},
		body: new URLSearchParams({
			grant_type: "password",
			username,
			password,
			client_id: "fizio",
		}),
	});

	if (res.ok) {
		const token = await res.json();
		console.log(token);
		token.name = username;
		return token;
	}

	return null;
}

export async function logout(refresh_token) {
	const res = await fetch(process.env.NEXT_PUBLIC_KEYCLOAK_LOGOUT, {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
		},
		body: new URLSearchParams({
			client_id: "fizio",
			refresh_token: refresh_token,
		}),
	});
	console.log(res);
}
