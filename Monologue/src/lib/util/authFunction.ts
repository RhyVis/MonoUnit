import CryptoJS from "crypto-js";
import { apiPost } from "@/lib/util/apiMethods";

const validateToken = async (token: string) => {
  if (token.length > 0) {
    try {
      const hashCode = CryptoJS.SHA256(token).toString(CryptoJS.enc.Hex);
      return (await apiPost("api/auth", hashCode)).data as boolean;
    } catch (error) {
      console.error(error);
      return false;
    }
  } else {
    return false;
  }
};

export { validateToken };
