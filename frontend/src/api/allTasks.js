import api from "./axios";

export const allTasks = () => {
  return api.get("/tasks");
};
