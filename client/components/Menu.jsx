import {
	HomeIcon,
	CalendarDaysIcon,
	ClockIcon,
	BellIcon,
	UserCircleIcon,
} from "@heroicons/react/20/solid";

import Link from "next/link";

export default function Menu() {
	return (
		<main className="fixed bottom-0 md:left-0 w-full md:w-32 h-16 md:h-full flex justify-center rounded-t-lg md:rounded-t-none items-center bg-[#0000FF]">
			<div className="flex md:flex-col w-full justify-around md:justify-center items-center md:space-x-0 md:space-y-12">
				<Link href="/settings" className="hidden md:block fixed top-8">
					<UserCircleIcon className="w-16 h-16"></UserCircleIcon>
				</Link>
				<Link href="/">
					<HomeIcon className="w-8 h-8"></HomeIcon>
				</Link>
				<Link href="/appointments">
					<CalendarDaysIcon className="w-8 h-8"></CalendarDaysIcon>
				</Link>
				<Link href="/history">
					<ClockIcon className="w-8 h-8"></ClockIcon>
				</Link>
				<Link href="/notifications">
					<BellIcon className="w-8 h-8"></BellIcon>
				</Link>
			</div>
		</main>
	);
}
