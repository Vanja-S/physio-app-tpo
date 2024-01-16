export default function Appointment({ appointment }) {
	const convertedStart = appointment.zacetek * 1000;
	const convertedEnd = appointment.konec * 1000;
	return (
		<div className="flex flex-col items-center p-4 pb-0">
			<h1 className="text-lg text-bold">
				dr. {appointment.fizioterapevtFullName}
			</h1>
			<small className="text-sm whitespace-pre-line text-center">
				{new Date(convertedStart).toLocaleDateString()}
				{"\n"}
				{new Date(convertedStart).toLocaleTimeString()} do{" "}
				{new Date(convertedEnd).toLocaleTimeString()}
			</small>
		</div>
	);
}
