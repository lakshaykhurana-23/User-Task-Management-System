import api from "./axios";

export const allUsers = () => {
  return api.get("/users");
};
