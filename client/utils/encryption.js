import Cryptr from "cryptr";

export function encrypt(plainText) {
	const secretKey = process.env.NEXTAUTH_SECRET;
	const cryptr = new Cryptr(secretKey);

	const cipherText = cryptr.encrypt(plainText);
	return cipherText;
}

export function decrypt(cipherText) {
	const secretKey = process.env.NEXTAUTH_SECRET;
	const cryptr = new Cryptr(secretKey);

	const plainText = cryptr.decrypt(plainText);
	return plainText;
}
