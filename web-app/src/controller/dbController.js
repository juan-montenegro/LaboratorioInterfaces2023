import { createConnection } from "mysql";

const myConnect = createConnection({
    host: "localhost",
    user: "camilo",
    password: "docWHn9LCLk7N98@",
    database: "db2023-2",
});
  
export default myConnect;