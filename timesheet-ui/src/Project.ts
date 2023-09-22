import { z } from "zod";

const Project = z.object({
  id: z.string(),
  name: z.string(),
  description: z.string(),
});

export default Project;
