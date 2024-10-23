import { getCurrentInstance } from "vue";
import type { ComponentInternalInstance, ComponentPublicInstance } from "vue";

export default () => (getCurrentInstance() as ComponentInternalInstance).proxy as ComponentPublicInstance;
