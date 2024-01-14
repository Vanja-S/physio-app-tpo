async function getPlanMetadata(username, token) {
	const url = `${process.env.NEXT_PUBLIC_BACKEND_URL}/pacienti/${username}/fizioplan`;

	const res = await fetch(url, {
		headers: {
			"Content-Type": "application/json",
			Authorization: "Bearer " + token,
		},
	});

	if (res.ok) {
		const data = await res.json();
		return data;
	}

	return null;
}

export default async function Plan({ username, token }) {
	const plan = await getPlanMetadata(username, token).catch((e) => {
		console.log(e);
	});

	return (
		<div>
			{plan && (
				<div className="flex flex-col p-8">
					<h1 className="text-lg text-medium">{plan.naslov}</h1>
					<small className="text-sm">{plan.poskodba}</small>
					<p className="text-md">
						Predviden čas rehabilitacije:{" "}
						{new Date(plan.trajanjeOd).getDate().toLocaleString()}-
						{new Date(plan.trajanjeDo).getDate().toLocaleString()}
					</p>
					<p className="text-md">{plan.opis}</p>
				</div>
			)}
			{!plan && <h1 className="text-lg">Nimate plana.</h1>}
		</div>
	);
}
