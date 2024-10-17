const fontList = [
  {
    name: "LilitaOne",
    url: "/font/LilitaOne-Regular.ttf",
  },
  {
    name: "ChildFunSans",
    url: "/font/ChildFunSans-Demo.ttf",
  },
  {
    name: "RoGSanSrfStd-Bd",
    url: "/font/RoGSanSrfStd-Bd_other_mod.woff2",
  },
  {
    name: "RoGSanSrfStd-Bd",
    url: "/font/RoGSanSrfStd-Bd_CJK.woff2",
  },
];

const base = import.meta.env.VITE_RES_ROOT;

async function loadFonts() {
  for (const item of fontList) {
    const font = new FontFace(item.name, `url(${base + item.url})`);
    const loaded = await font.load();
    document.fonts.add(loaded);
    console.log(`Successfully loaded ${item.name}`);
  }
}

export { loadFonts };
