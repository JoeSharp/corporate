import { z } from "zod";

const Project = z.object({
  id: z.string(),
  name: z.string(),
  description: z.string(),
});

export type IProject = z.infer<typeof Project>;

export default Project;
