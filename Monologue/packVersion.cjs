const fs = require("fs");

// Build time record
const version = new Date().getTime();
const versionObject = { compileTime: version };
const versionJson = JSON.stringify(versionObject);

console.log(`Version confirmed on ${new Date(version)}`);

fs.writeFile("./public/version.json", versionJson, err => {
  if (err) console.error(err);
  console.log("Version file written to public");
});

if (!fs.existsSync("./src/assets/local")) {
  fs.mkdirSync("./src/assets/local", { recursive: true });
}

fs.writeFile("./src/assets/local/version.json", versionJson, err => {
  if (err) console.error(err);
  console.log("Version file written to src");
});
