import https from "./axiosCrud";
const create = (url, data) => {
  return https.post(url, data, {});
};
const get = (url, response_type = "") => {
  if (response_type === "") {
    return https.get(url);
  } else {
    return https.get(url, { responseType: response_type });
  }
};
const put = (url, data) => {
  return https.put(url, data, {});
};
const remove = (url) => {
  return https.delete(url);
};
export default { create, get, put, remove };
