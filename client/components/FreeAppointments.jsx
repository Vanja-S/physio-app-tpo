"use client";

import { useEffect, useState } from "react";

import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

import Appointment from "@/components/Appointment";
import BookAppointment from "@/components/BookAppointment";

export default function FreeAppointments({ session }) {
	const [value, onChange] = useState(new Date());
	const [freeAppointments, setFreeAppointments] = useState([]);

	const appointments = [
		{
			terminId: 1,
			zacetek: 1705298400,
			konec: 1705302000,
			jeZaseden: false,
			fizioterapevtUsername: "mg7954",
			fizioterapevtFullName: "Meghan Gamble",
		},
		{
			terminId: 3,
			zacetek: 1706176800,
			konec: 1706180400,
			jeZaseden: false,
			fizioterapevtUsername: "wn2649",
			fizioterapevtFullName: "Walter Nolan",
		},
		{
			terminId: 5,
			zacetek: 1707030000,
			konec: 1707033600,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
		{
			terminId: 7,
			zacetek: 1707919200,
			konec: 1707922800,
			jeZaseden: false,
			fizioterapevtUsername: "mg7954",
			fizioterapevtFullName: "Meghan Gamble",
		},
		{
			terminId: 8,
			zacetek: 1708329600,
			konec: 1708333200,
			jeZaseden: false,
			fizioterapevtUsername: "wn2649",
			fizioterapevtFullName: "Walter Nolan",
		},
		{
			terminId: 10,
			zacetek: 1709186400,
			konec: 1709190000,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
		{
			terminId: 11,
			zacetek: 1709622000,
			konec: 1709625600,
			jeZaseden: false,
			fizioterapevtUsername: "wn2649",
			fizioterapevtFullName: "Walter Nolan",
		},
		{
			terminId: 12,
			zacetek: 1710057600,
			konec: 1710061200,
			jeZaseden: false,
			fizioterapevtUsername: "mg7954",
			fizioterapevtFullName: "Meghan Gamble",
		},
		{
			terminId: 14,
			zacetek: 1710936000,
			konec: 1710939600,
			jeZaseden: false,
			fizioterapevtUsername: "mg7954",
			fizioterapevtFullName: "Meghan Gamble",
		},
		{
			terminId: 15,
			zacetek: 1711360800,
			konec: 1711364400,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
		{
			terminId: 16,
			zacetek: 1711796400,
			konec: 1711800000,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
		{
			terminId: 17,
			zacetek: 1712228400,
			konec: 1712232000,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
		{
			terminId: 18,
			zacetek: 1712662200,
			konec: 1712665800,
			jeZaseden: false,
			fizioterapevtUsername: "wn2649",
			fizioterapevtFullName: "Walter Nolan",
		},
		{
			terminId: 19,
			zacetek: 1713096900,
			konec: 1713100500,
			jeZaseden: false,
			fizioterapevtUsername: "mg7954",
			fizioterapevtFullName: "Meghan Gamble",
		},
		{
			terminId: 20,
			zacetek: 1713531600,
			konec: 1713535200,
			jeZaseden: false,
			fizioterapevtUsername: "cl0375",
			fizioterapevtFullName: "Chadwick Lamb",
		},
	];

	const handleClick = () => {
		var filteredAppointments = [...appointments];
		filteredAppointments = filteredAppointments.filter(
			(appointment) =>
				new Date(appointment.zacetek * 1000).toDateString() ==
				new Date(value).toDateString()
		);
		appointments.map((app) =>
			console.log(new Date(app.zacetek * 1000).toDateString())
		);
		setFreeAppointments(filteredAppointments);
	};

	return (
		<div className="flex flex-col md:flex-row w-full gap-4">
			<div className="flex flex-col items-start w-full md:w-fit justify-center h-full">
				<Calendar
					locale={"en-GB"}
					onChange={onChange}
					value={value}
					className="p-8 rounded-md"
				></Calendar>
				<div
					className="flex flex-col w-full items-center text-white"
					onClick={handleClick}
				>
					<button className="btn text-xl bg-carribeanCurrent rounded-md px-2 py-1 mt-4">
						Najdi
					</button>
				</div>
			</div>
			<div className="flex flex-col w-full md:w-full h-full overflow-hidden">
				{freeAppointments.length > 0 &&
					freeAppointments.map((appointment, index) => {
						return (
							<div
								className="flex flex-col text-white border border-platinum rounded"
								key={index}
							>
								<Appointment
									appointment={appointment}
								></Appointment>
								<div className="flex w-full justify-center pt-2 pb-4">
									<BookAppointment
										id={appointment.terminId}
										token={session?.token}
										username={
											session.user.preferred_username
										}
									></BookAppointment>
								</div>
							</div>
						);
					})}
				{freeAppointments.length == 0 && (
					<p className="text-lg text-white">
						Za ta datum ni prostih terminov.
					</p>
				)}
			</div>
		</div>
	);
}
