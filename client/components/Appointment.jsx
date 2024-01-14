export default function Appointment({ appointment }) {
	return (
		<div className="flex flex-col items-center p-4">
			<h1 className="text-lg text-bold">
				dr. {appointment.fizioterapevtFullName}
			</h1>
			<small className="text-sm">
				{new Date(appointment.zacetek).toLocaleDateString()} od{" "}
				{new Date(appointment.zacetek).getHours()}:
				{new Date(appointment.zacetek).getMinutes()} do{" "}
				{new Date(appointment.konec).getHours()}:
				{new Date(appointment.konec).getMinutes()}
			</small>
		</div>
	);
}
