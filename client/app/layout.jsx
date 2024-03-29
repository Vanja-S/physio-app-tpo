import { Inter, Kanit } from "next/font/google";
import "./globals.css";

const inter = Kanit({
	subsets: ["latin"],
	weight: ["100", "200", "300", "400", "500", "600", "700", "800", "900"],
});

// Session, auth, security
import Provider from "@/utils/SessionProviderWrapper";

import Menu from "@/components/Menu";
import { getServerSession } from "next-auth";
import { authOptions } from "@/utils/Auth";

import Image from "next/image";
import BackgroundImage from "@/public/images/background.svg";

export const metadata = {
	title: "Create Next App",
	description: "Generated by create next app",
};

export default async function RootLayout({ children }) {
	const session = await getServerSession(authOptions);
	return (
		<html lang="en">
			<Provider>
				<body className={inter.className}>
					<Image
						src={BackgroundImage}
						fill
						sizes="100vw"
						style={{
							objectFit: "cover",
							zIndex: -1,
							filter: "blur(4px)",
						}}
						alt="Background"
					></Image>
					<div className="md:ml-20 text-jet">{children}</div>
					{session && <Menu></Menu>}
				</body>
			</Provider>
		</html>
	);
}
