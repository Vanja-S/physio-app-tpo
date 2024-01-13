import type { Config } from "tailwindcss";

const config: Config = {
	content: [
		"./pages/**/*.{js,ts,jsx,tsx,mdx}",
		"./components/**/*.{js,ts,jsx,tsx,mdx}",
		"./components/*.{js,ts,jsx,tsx,mdx}",
		"./app/**/*.{js,ts,jsx,tsx,mdx}",
	],
	theme: {
		colors: {
			carribeanCurrent: "#3C6E71",
			jet: "#353535",
			platinum: "#D9D9D9",
			white: "#FFFFFF",
			indigoDye: "#284B63",
		},
	},
	plugins: [],
};
export default config;
