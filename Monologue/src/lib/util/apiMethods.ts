import instance from "@/lib/util/apiHttp";
import type { ApiResponse, CompileTime } from "@/lib/type/typeApi";

class PostObject {
  private code: number;
  private meta: string;
  private data: any;
  constructor(data: any) {
    this.code = 0;
    this.meta = "";
    this.data = data;
  }
}

/**
 * Get Method
 * @param url
 * @param params
 */
async function apiGet(url: string, params?: any): Promise<ApiResponse> {
  return (await instance.get(url, params)).data as ApiResponse;
}

/**
 * Post Method
 * @param url
 * @param data
 */
async function apiPost(url: string, data?: any): Promise<ApiResponse> {
  return (await instance.post(url, new PostObject(data))).data as ApiResponse;
}

/**
 * Put Method
 * @param url
 * @param data
 */
async function apiPut(url: string, data?: any): Promise<ApiResponse> {
  return (await instance.put(url, data)).data as ApiResponse;
}

/**
 * Delete Method
 * @param url
 */
async function apiDelete(url: string): Promise<ApiResponse> {
  return (await instance.delete(url)).data as ApiResponse;
}

async function getVersion(): Promise<number> {
  return ((await instance.get("/version.json")).data as CompileTime).compileTime;
}

export { apiGet, apiPost, apiPut, apiDelete, getVersion };
