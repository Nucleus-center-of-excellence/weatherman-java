
import { Injectable } from '@angular/core';
/// <reference types="crypto-js" />
import {AES, enc,  mode, pad } from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  iv = enc.Utf8.parse('7061737323313233');
  key1 = enc.Utf8.parse('7061737323313233');

  /**
   * This function is called to Write 'Key' from Session Storage
   * It also Encrypt the key and stores it in session
   * @param {string} key
   * @param value
   */
  write(key: string, value: any) {
    let  encrypted;
    if (value) {
      value = JSON.stringify(value);
      encrypted  = <any>AES.encrypt(enc.Utf8.parse(value), this.key1,
        {
          keySize: 128 / 8,
          iv: this.iv,
          mode: mode.CBC,
          padding: pad.Pkcs7
        });
    }
    sessionStorage.setItem(key, encrypted);
  }

  /**
   * This function is called to read 'Key' from Session Storage.
   * It also Decrypt the key and stores it in session
   * @param {string} key
   * @returns {T}
   */
  read<T>(key: string): T {
    let decrypted: any;

      const value = sessionStorage.getItem(key);
      if (value && value !== 'undefined' && value !== 'null') {
      //  return <T>JSON.parse(value);
      decrypted = AES.decrypt(value, this.key1, {
        keySize: 128 / 8,
        iv: this.iv,
        mode: mode.CBC,
        padding: pad.Pkcs7
      });
      const decryptedCode = decrypted.toString(enc.Utf8);
      return <T>JSON.parse(decryptedCode);
    }
    return null;
  }

  /**
   * This function is called to delete 'Key' from Session Storage
   * @param {string} key
   */
  delete(key: string) {
    sessionStorage.removeItem(key);
  }
clearStorage() {
    sessionStorage.clear();
}
}
