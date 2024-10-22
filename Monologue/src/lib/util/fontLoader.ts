const fontList = [
  {
    name: "YurukaStd",
    url: "/font/YurukaStd.woff2",
  },
  {
    name: "SSFangTangTi",
    url: "/font/ShangShouFangTangTi.woff2",
  },
  {
    name: "LilitaOne",
    url: "/font/LilitaOne-Regular.ttf",
  },
  {
    name: "ChildFunSansFusion-Z",
    url: "/font/ChildFunSansFusion-Z.ttf",
  },
  {
    name: "RoGSanSrfStd-Bd",
    url: "/font/RoGSanSrfStd-Bd_other_mod.woff2",
  },
  {
    name: "RoGSanSrfStd-Bd",
    url: "/font/RoGSanSrfStd-Bd_CJK.woff2",
  },
  {
    name: "RoGSanSrfStd-Bd",
    url: "/font/RoGSanSrfStd-Bd-Original.woff2",
  },
  {
    name: "GlowSans",
    url: "/font/GlowSansSC-Normal-Heavy.otf",
  },
  {
    name: "IosevkaRx",
    url: "/font/IosevkaRx-Regular.woff2",
  },
  {
    name: "IosevkaRx",
    url: "/font/IosevkaRx-Bold.woff2",
  },
];

const base = import.meta.env.VITE_RES_ROOT;

async function loadFonts() {
  try {
    for (const font of fontList) {
      const fontFace = new FontFace(font.name, `url(${base + font.url})`);
      const loaded = await fontFace.load();
      document.fonts.add(loaded);
      console.debug(`Successfully loaded ${font.name}`);
    }
    console.log("Successfully loaded all fonts");
  } catch (e) {
    throw e;
  }
}

export { loadFonts };
