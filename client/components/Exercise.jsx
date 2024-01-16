import Image from "next/image";

export default function Exercise({ exercise }) {
	return (
		<div className="w-full h-fit py-2 flex flex-col rounded">
			{/* <Image src={}></Image> */}
			<h1 className="text-lg">{exercise.imeVaje}</h1>
			<small className="text-md">{exercise.ponovitve}</small>
		</div>
	);
}
