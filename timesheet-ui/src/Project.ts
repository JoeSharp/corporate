import { z } from "zod";

const LoggedInUser = z.object({
  username: z.string(),
  accessToken: z.string()
});
export type ILoggedInUser = z.infer<typeof LoggedInUser>;

const Project = z.object({
  id: z.string(),
  name: z.string(),
  description: z.string(),
});

export type IProject = z.infer<typeof Project>;

export default Project;
