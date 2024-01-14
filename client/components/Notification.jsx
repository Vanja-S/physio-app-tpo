export default function Notification({ notification }) {
	return (
		<div className="flex flex-col">
			<h1 className="text-lg text-bold">{notification.naslov}</h1>
			<small className="text-xs">{new Date(notificaton.ts)}</small>
			<p className="text-md">{notification.vsebina}</p>
		</div>
	);
}
