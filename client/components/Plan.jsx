"use client";

import Exercise from "@/components/Exercise";
import { useState } from "react";
import { XMarkIcon } from "@heroicons/react/20/solid";
import { useRouter } from "next/navigation";

export default function Plan({ plan }) {
	const router = useRouter();

	const conversion = {
		od: new Date(plan.trajanjeOd * 1000).toLocaleDateString(),
		do: new Date(plan.trajanjeDo * 1000).toLocaleDateString(),
	};

	const exercises = [
		{
			vnosId: 1,
			imeVaje: "Bird dog",
			ponovitve: "3 x 12",
			opombaVnosa: null,
		},
		{
			vnosId: 2,
			imeVaje: "Stretching wrist flexors",
			ponovitve: "3 x 12",
			opombaVnosa: null,
		},
		{
			vnosId: 3,
			imeVaje: "Extension lunge",
			ponovitve: "3 x 12",
			opombaVnosa: null,
		},
	];

	const [comments, setComments] = useState([
		{ id: 1, data: null },
		{ id: 2, data: null },
		{ id: 3, data: null },
	]);
	const [workoutStarted, setWorkoutStarted] = useState(false);
	const [success, setSuccess] = useState(false);

	const showModal = (e) => {
		setWorkoutStarted(true);
	};

	const hideModal = (e) => {
		setWorkoutStarted(false);
	};

	const handleSubmit = (e) => {
		e.preventDefault(e);
		setSuccess(true);
		setTimeout(() => {
			setSuccess(false);
			setWorkoutStarted(false);
		}, 1000);
	};

	const handleChange = (e) => {
		setComments(
			comments.map((comment, i) =>
				i == e.target.id
					? { ...comment, data: e.target.value }
					: comment
			)
		);
	};

	return (
		<div>
			<div className="flex flex-col mt-4 whitespace-pre-line relative h-full">
				<h1 className="text-lg font-bold mb-2">
					<span className="underline underline-2 decoration-2 decoration-jet">
						Naziv fizioplana
					</span>
					{"\n"}
					<span className="no-underline underline-0 decoration-0 font-light">
						{plan.naslov}
					</span>
				</h1>
				<h1 className="text-lg font-bold mb-2">
					<span className="underline underline-2 decoration-2 decoration-jet">
						Vrsta poškodbe
					</span>
					{"\n"}
					<span className="no-underline underline-0 decoration-0 font-light">
						{plan.poskodba}
					</span>
				</h1>
				<h1 className="text-lg font-bold mb-2">
					<span className="underline underline-2 decoration-2 decoration-jet">
						Predviden čas rehabilitacije
					</span>
					{"\n"}
					<span className="no-underline font-light">
						{conversion.od}
						{" do "}
						{conversion.do}
					</span>
				</h1>
				<h1 className="text-lg font-bold">
					<span className="underline underline-2 decoration-2 decoration-jet">
						Opis rehabilitacijskega procesa
					</span>
					{"\n"}
					<span className="no-underline underline-0 decoration-0 font-light">
						{plan.opis}
					</span>
				</h1>
				<button
					className="btn text-xl rounded-lg bg-carribeanCurrent w-fit px-2 py-1 mt-4"
					onClick={showModal}
				>
					Začni trening
				</button>
			</div>
			{workoutStarted && (
				<div className="top-0 left-0 absolute z-1 bg-jet/70 w-full h-full flex justify-center items-center">
					<button
						className="absolute top-4 right-4"
						onClick={hideModal}
					>
						<XMarkIcon className="w-8 h-8"></XMarkIcon>
					</button>
					<div className="w-1/2 h-2/3 bg-jet rounded-lg p-4 relative flex flex-col text-white">
						{exercises.map((exercise, index) => {
							return (
								<div className="flex items-center">
									<Exercise exercise={exercise}></Exercise>
									<input
										onChange={handleChange}
										id={index}
										type="text"
										placeholder="Komentar"
										className="py-2 px-4 rounded h-fit text-jet"
									/>
								</div>
							);
						})}
						<button
							className="btn px-6 py-2 cursor-pointer bg-carribeanCurrent font-bold text-sm text-white w-fit rounded bottom-4 absolute"
							onClick={handleSubmit}
						>
							Zaključi trening
						</button>
					</div>
				</div>
			)}
			{success && (
				<div className="absolute bottom-4 right-4 bg-[#00FF00]/50 p-4 w-52 h-fit text-center text-md">
					Zaključili ste trening.
				</div>
			)}
		</div>
	);
}
