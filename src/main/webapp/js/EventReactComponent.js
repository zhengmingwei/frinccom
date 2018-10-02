import React from 'react';
import Network from 'tigerfacejs-network';
export default class EventReactComponent extends React.Component {
    constructor(props) {
        super(props);
        this._eventsMap_ = {};
    }

    /**
     *  添加事件侦听
     * @param key
     * @param func
     */
    addEventListener(key, func) {
        //console.log("注册事件", key);
        this._eventsMap_[key] = func;
        E.addEventListener(key, func);
    }

    addEventListenerAndFetchLast(key, func) {
        //console.log("注册事件", key);
        this._eventsMap_[key] = func;
        E.addEventListenerAndFetchLast(key, func);
    }

    addOneTimeEventListener(key, func) {
        //console.log("注册事件", key);
        this._eventsMap_[key] = func;
        E.addOneTimeEventListener(key, func);
    }

    /**
     * 发送异步事件
     * @param key
     * @param func
     */
    dispatchEvent(key,func){
        E.dispatchEvent(key,func);
    }

    /**
     * 发送同步事件
     * @param key
     * @param func
     */
    dispatchAsyncEvent(key,func){
       E.dispatchAsyncEvent(key,func);
    }

    /**
     * rest ful PUT方式请求
     * @param name
     * @param data
     */
    doModify(name, data){
        E.doModify(name,data);
    }

    /**
     * rest ful DELETE方式请求
     * @param name
     * @param data
     */
    doRemove(name, data){
        E.doRemove(name, data);
    }

    /**
     * rest ful POST方式请求
     * @param name
     * @param data
     */
    doAdd(name, data){
        E.doAdd(name, data);
    }

    /**
     * rest ful GET方式请求
     * @param name
     * @param data
     */
    doFind(name, data){
        E.doFind(name, data);
    }

    /**
     * rest ful GET方式请求(url参数拼写)
     * @param name
     * @param id
     */
    doGet(name, id){
        E.doGet(name, id);
    }

    /**
     * 组件销毁时 同时销毁事件侦听
     */
    componentWillUnmount() {
       // console.log("销毁事件");
        for (let key in this._eventsMap_) {
            E.removeEventListener(key, this._eventsMap_[key]);
            this._eventsMap_[key] = undefined;
         //   console.log("销毁",key);
        }
    }
};
