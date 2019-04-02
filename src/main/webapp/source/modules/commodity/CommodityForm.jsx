import React from "react";

import axios from 'axios';


import ReactDOM from 'react-dom';
import {connect} from "react-redux";

import {Button, Col, DatePicker, Form, Input, Row, Select} from "antd";
import {showModalDialog} from "actions/CommonAction";
import {saveCommodity_1,saveCommodity_2,saveCommodity, getCommodity, selectCommodity} from "actions/CommodityActions";
import {industryAndCategory} from "actions/DictionaryActions";
import {receiveOtherQualificationList} from "actions/OtherQualificationActions";
import {receiveSpecialItemList} from "actions/SpecialItemActions";
import SubPage from "modules/common/SubPage";
import ImgUpload from "modules/common/ImgUpload";
import VideoUpload from "modules/common/VideoUpload"
import SpecialItemList from "modules/commodity/SpecialItemList";
import OtherQualificationList from "modules/commodity/OtherQualificationList";
// import {BrandForm} from "modules/commodity/BrandForm";
import moment from 'moment';
import {objToStrMap, errorMessages} from 'modules/common/CommonUtils';
const FormItem = Form.Item;
const createForm = Form.create;
const Option = Select.Option;

class CommodityForm extends React.Component {

    state = {
    	 list:"",
         company:{name:'',idCode:''},
  	};
    constructor(props) {
        super(props);
    }

    componentWillMount() {
         // this.props.dispatch(getCommodity(this.props.params.id));
        this.props.dispatch(industryAndCategory());
        console.log("form will mount")
        if(!this.props.params.id) {
            this.props.dispatch(selectCommodity({name: "", category: "", industry:"", pic:"",sp_video:"", company:{}, factory:{}, brand:"",otherQualifications:[],specialItems:[]}));
            this.props.dispatch(receiveOtherQualificationList([]));
            this.props.dispatch(receiveSpecialItemList([]));
        }
    }
    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;
        console.log("nextPropsnextProps+++++",nextProps);
        console.log("this.props.params.id+++++",this.props.params.id)
        if (statusId != oldStatusId){
            console.log("nextPropsnextProps+++++",nextProps)
            // this.props.dispatch(getCommodity(statusId));
            this.props.dispatch(selectCommodity({name: "", category: "", industry:"", pic:"",sp_video:"", company:{}, factory:{}, brand:"",otherQualifications:[],specialItems:[]}));
            this.props.dispatch(receiveOtherQualificationList([]));
            this.props.dispatch(receiveSpecialItemList([]));
            return false;
        }
        return true;
    }

    reset() {
        this.props.form.resetFields();
        //this.props.dispatch(userCriteriaChanged());
    }
//---------------------------------------------
    handleSubmit_1 = ()=>{
        let rows = this.props.form.getFieldsValue(); 
        const {company} = rows; 
        var url = "http://47.105.123.55:9999/company/base/"+company.name1;
         axios.get(url).then(res=>{
         		 const posts = res.data.data;
                 console.log(company);
                 console.log(posts);
                 console.log(posts.createTimes);

                 // businessTime "2018-08-14 至 2048-08-13"
                 let businessTime = posts.businessTime;
                 let endTime = null;
                 let bbTime = businessTime.split(' 至 ')[1];
                 if(bbTime=='无固定期限'){
                     endTime=moment().add('days',36500).format('YYYY-MM-DD');
                 }else{
                     endTime=posts.businessTime.split(' 至 ')[1]
                 }

                 this.props.form.setFieldsValue(
                     {
                         company:{
                                 name: posts.name,
                                 idCode:posts.regCode,
                                 legalPerson:posts.legalPerson,
                                 mail:posts.legalPerson,
                                 mphone:posts.legalPerson,
                                 phone:posts.tel,
                                 regAddr:posts.checkInAddr,
                                 businessScope:posts.scope,
                                // businessBegin:new Date(),
                                 businessBegin:moment(posts.createTime),
                                 businessEnd:moment(endTime),
                         }
                     });
         });
    }
    
//---------------------------------------------
//---------------------------------------------************************************************
    handleSubmit_2 = ()=>{
        E.addOneTimeEventListener("tocommoditylist",  (e) => {
            this.props.history.push('/manager/commodity/list/1');
        })
        this.props.form.validateFields((err, values) => {

                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                const {specialItemList} = this.props;
                rows.specialItems = specialItemList;
                const {otherQualificationList} = this.props;
                rows.otherQualifications = otherQualificationList;
                this.props.dispatch(saveCommodity_2(rows))

        })

    }

//---------------------------------------------************************************************
    handleSubmit() {
        E.addOneTimeEventListener("tocommoditylist",  (e) => {
            this.props.history.push('/manager/commodity/list/1');
        })
        this.props.form.validateFields((err, values) => {
            if (!err) {
                let rows = {};
                Object.assign(rows, this.props.form.getFieldsValue());
                const {specialItemList} = this.props;
                rows.specialItems = specialItemList;
                const {otherQualificationList} = this.props;
                rows.otherQualifications = otherQualificationList;
                this.props.dispatch(saveCommodity(rows))
            } else {
                console.error('validate failed');
                console.log(err,values);
                let errstr = errorMessages(err);
                alert(errstr);
            }
        })
    }

    normFile = (e) => {
        return e;
        // if (Array.isArray(e)) {
        //     return e;
        // }
        // let result = "";
        // if (e.file.response) {
        //     result = e.file.response.result;
        //     console.log(JSON.stringify(result));
        //     // return JSON.stringify(result);
        // }
        //
        // // return JSON.stringify(e.file.response) ;
        // return e && e.fileList;
    }

    addSpecialItem = () => {

    }

    industryChange = (value) => {
        // this.setState({currentIndustry: value});
        this.props.form.setFieldsValue({
            category:"",
        });

    }

    render() {
    	var styles={
    		position:'absolute'
    	}
   
       
    
         const list = this.props.list;
        const {getFieldDecorator} = this.props.form;
        const {selectedCommodity:{id, name, category, industry, pic,sp_video, company, factory, brand}} = this.props;
        let cBusinessBegin = company?moment(company.businessBegin):moment();
        let cBusinessEnd = company?moment(company.businessEnd):moment();
        let fBusinessBegin = factory?moment(factory.businessBegin):moment();
        let fBusinessEnd = factory?moment(factory.businessEnd):moment();
        
        let cname = company?moment(company.name):moment();
        
        
        const currentIndustry = this.props.form.getFieldValue("industry");
        const commodityKey = this.props.params.id || 'defaultCommodityKey';
        console.log("picpidpic",pic)
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 3},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };
        const picLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 9},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 12},
            },
        };

        const doubleFormItemLayout = {
            labelCol: {
                xs: {span: 12},
                sm: {span: 6},
            },
            wrapperCol: {
                xs: {span: 12},
                sm: {span: 12},
            }
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 14,
                    offset: 6,
                },
            },
        };

        const {industryAndCategory} = this.props;
        const industryAndCategoryMap = objToStrMap(industryAndCategory);
        let industryOptions = [];
        let categoryMap = new Map();
        if(industryAndCategoryMap)industryAndCategoryMap.forEach((optionValue, optionKey) => {
            industryOptions.push(<Option key={optionKey+""} value={optionKey+""}>{optionValue.value}</Option>);
            let categoryOptins = [];
            if(optionValue.children)optionValue.children.forEach((item)=>{
                categoryOptins.push(<Option key={item.id+""} value={item.id+""}>{item.value}</Option>)
            });
            categoryMap.set(optionKey, categoryOptins);
        })

        return (
            <SubPage breadcrumb="/后台管理/认证管理中心/发布企业认证信息">
                <Form key={commodityKey}>
                    {getFieldDecorator('id', {
                        initialValue: id,
                    })(
                        <Input type="hidden"/>
                    )}
                    <section className="markdown">
                        <h3>商品基本信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <div >
                        {/*<section><h1>发布企业认证信息</h1></section>*/}


                        <FormItem
                            {...formItemLayout}
                            label={"所属行业"}
                            hasFeedback
                        >
                            {getFieldDecorator('industry', {
                                rules: [
                                    {required: true, message: '请输入所属行业!'},
                                ],
                                initialValue: industry,
                            })(
                                <Select placeholder="请输入所属行业" onSelect={this.industryChange}>
                                    {industryOptions}
                                </Select>
                            )}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label="商品名称"
                            hasFeedback
                        >
                            {getFieldDecorator('name', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入商品名称.",
                                }],
                                initialValue: name,
                            })(
                                <Input placeholder="商品名称"/>
                            )}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label={"所属类别"}
                            hasFeedback
                        >
                            {getFieldDecorator('category', {
                                rules: [
                                    {required: true, message: '请输入所属类别!'},
                                ],
                                initialValue: category,
                            })(
                                <Select placeholder="请选择所属类别">
                                    {categoryMap.get(currentIndustry)}
                                </Select>
                            )}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label="商品图片"
                        >
                            {getFieldDecorator('pic', {
                                valuePropName: 'fileList',
                                getValueFromEvent: this.normFile,
                            })(
                                <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={pic}>
                                </ImgUpload>


                            )}
                        </FormItem>
                        <FormItem
                            {...formItemLayout}
                            label="商品图片2"
                        >
                            {getFieldDecorator('sp_video', {
                                valuePropName: 'fileList',
                                getValueFromEvent: this.normFile,
                            })(
                                <VideoUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={sp_video}>
                                </VideoUpload>
                            )}
                        </FormItem>
                        <Row gutter={40} style={{marginBottom: "12px"}}><Col span={24}>
                        <SpecialItemList {...this.props} editAble={true}/>
                        </Col></Row>
                    </div>
                    {/*brand begin*/}
                    <section className="markdown">
                        <h3>品牌信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    <div style={{backgroundColor: "#f5f5f5"}}>
                        <br/>
                        {getFieldDecorator('brand.id', {
                            initialValue: brand ? brand.id: "",
                        })(
                            <Input type="hidden"/>
                        )}
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌名称"
                            hasFeedback
                        >
                            {getFieldDecorator('brand.name', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入品牌名称.",
                                }],
                                initialValue: brand.name,
                            })(
                                <Input placeholder="品牌名称"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌注册号"
                            hasFeedback
                        >
                            {getFieldDecorator('brand.code', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入品牌注册号.",
                                }],
                                initialValue: brand.code,
                            })(
                                <Input placeholder="品牌注册号"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="品牌权利人"
                            hasFeedback
                        >
                            {getFieldDecorator('brand.owner', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入品牌权利人.",
                                }],
                                initialValue: brand.owner,
                            })(
                                <Input placeholder="品牌权利人"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"品牌状态"}
                            hasFeedback
                        >
                            {getFieldDecorator('brand.brandStatus', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [
                                    {required: true, message: '请输入品牌状态!'},
                                ],
                                initialValue: brand.brandStatus,
                            })(
                                <Select placeholder="请选择品牌状态">
                                    <Option value="142">R</Option>
                                    <Option value="143">TM</Option>
                                </Select>
                            )}
                        </FormItem>
                    </Col></Row>
                    <Row><Col span={8}>
                        <FormItem
                            {...picLayout}
                            label="商标注册证"
                        >
                            {getFieldDecorator('brand.registrationCertificate', {
                                valuePropName: 'fileList',
                                getValueFromEvent: this.normFile,
                            })(
                                <ImgUpload name="uploadFile" action="/incc/file/upload/2/1" initialValue={brand.registrationCertificate}>

                                </ImgUpload>
                            )}
                        </FormItem>
                    </Col>
                        <Col span={8}>
                            <FormItem
                                {...picLayout}
                                label="受理通知书"
                            >
                                {getFieldDecorator('brand.notification', {
                                    valuePropName: 'fileList',
                                    getValueFromEvent: this.normFile,
                                })(
                                    <ImgUpload name="uploadFile" action="/incc/file/upload/2/1" initialValue={brand.notification}>

                                    </ImgUpload>
                                )}
                            </FormItem>
                        </Col>
                        <Col span={8}>
                            <FormItem
                                {...picLayout}
                                label="授权书扫描件"
                            >
                                {getFieldDecorator('brand.authorization', {
                                    valuePropName: 'fileList',
                                    getValueFromEvent: this.normFile,
                                })(
                                    <ImgUpload name="uploadFile" action="/incc/file/upload/2/1" initialValue={brand.authorization}>

                                    </ImgUpload>
                                )}
                            </FormItem>
                        </Col>
                    </Row>
                    </div>
                    {/*Brand end*/}
                    <section className="markdown">
                        <h3>商品其他资质</h3>
                    </section>
                    <hr/>
                    <br/>
                    <OtherQualificationList {...this.props} currentIndustry={currentIndustry} editAble={true}/>
                    {/*经营企业基本信息 begin*/}
                    <section className="markdown">
                        <h3>经营企业基本信息</h3>
                    </section>
                    <hr/>
                    <br/>
                    {getFieldDecorator('company.id', {
                        initialValue: company ? company.id: "",
                    })(
                        <Input type="hidden"/>
                    )}
                    <div style={{backgroundColor: "#f5f5f5"}}>
                        <br/>
                    
                     <FormItem 
                        {...formItemLayout}
                        label="企业名称或企业信用代码"
                        hasFeedback
                    >
                        {getFieldDecorator('company.name1', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业名称或企业信用代码",
                            }],
                            initialValue: company.name1,
                        })(
                            <Input placeholder="企业名称或企业信用代码" />
                        )}
                      
                    	 <Button type="primary" htmlType="button" size="large" style={styles}
                                onClick={() => this.handleSubmit_1()} >一键填充****</Button>
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="企业名称"
                        hasFeedback
                    >
                        {getFieldDecorator('company.name', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业名称",
                            }],
                            initialValue: company.name,
                        })(
                            <Input placeholder="请输入企业名称"  />
                        )}
                       
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业身份代码"
                            hasFeedback
                        >
                            {getFieldDecorator('company.idType', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入企业身份代码.",
                                }],
                                initialValue: company.idType,
                            })(
                                <Select placeholder="请选择企业身份代码">
                                    <Option value="138">组织机构代码</Option>
                                    <Option value="139">社会统一信用代码</Option>
                                </Select>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="输入身份代码"
                            hasFeedback
                        >
                            {getFieldDecorator('company.idCode', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入身份代码.",
                                }],
                                initialValue: company.idCode,
                            })(
                                <Input placeholder="输入身份代码" />
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业类型"
                            hasFeedback
                        >
                            {getFieldDecorator('company.companyType', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入企业类型.",
                                }],
                                initialValue: company.companyType,
                            })(
                                <Select placeholder="请选择企业类型">
                                    <Option value="140">有限责任公司</Option>
                                    <Option value="141">股份有限公司</Option>
                                </Select>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"法定代表人"}
                            hasFeedback
                        >
                            {getFieldDecorator('company.legalPerson', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入法定代表人.",
                                }],
                                initialValue: company.legalPerson,
                            })(
                                <Input placeholder="法定代表人"/>
                            )}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label="企业注册地址"
                        hasFeedback
                    >
                        {getFieldDecorator('company.regAddr', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业注册地址.",
                            }],
                            initialValue: company.regAddr,
                        })(
                            <Input placeholder="企业名称"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="企业经营地址"
                        hasFeedback
                    >
                        {getFieldDecorator('company.businessAddr', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业经营地址.",
                            }],
                            initialValue: company.businessAddr,
                        })(
                            <Input placeholder="企业名称"/>
                        )}
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限自"
                            hasFeedback
                        >
                            {getFieldDecorator('company.businessBegin', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    message: "请输入营业期限自.",
                                }],
                                 initialValue: cBusinessBegin,
                            })(
                                <DatePicker />
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限至"
                            hasFeedback
                        >
                            {getFieldDecorator('company.businessEnd', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    message: "请输入经营期限至.",
                                }],
                                initialValue: cBusinessEnd,
                            })(
                                <DatePicker />
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="联系人"
                            hasFeedback
                        >
                            {getFieldDecorator('company.contacter', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入联系人.",
                                }],
                                initialValue: company.contacter,
                            })(
                                <Input placeholder="联系人"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"联系电话"}
                            hasFeedback
                        >
                            {getFieldDecorator('company.phone', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入联系电话.",
                                }],
                                initialValue: company.phone,
                            })(
                                <Input placeholder="联系电话"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="电子邮件"
                            hasFeedback
                        >
                            {getFieldDecorator('company.mail', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入电子邮件.",
                                }],
                                initialValue: company.mail,
                            })(
                                <Input placeholder="电子邮件"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"移动电话"}
                            hasFeedback
                        >
                            {getFieldDecorator('company.mphone', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入移动电话.",
                                }],
                                initialValue: company.mphone,
                            })(
                                <Input placeholder="移动电话"/>
                            )}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label={"经营范围"}
                        hasFeedback
                    >
                        {getFieldDecorator('company.businessScope', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入经营范围.",
                            }],
                            initialValue: company.businessScope,
                        })(
                            <Input placeholder="经营范围"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="营业执照副本扫描件"
                    >
                        {getFieldDecorator('company.businessLicense', {
                            valuePropName: 'fileList',
                            // getValueFromEvent: this.normFile,
                        })(
                            <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={company.businessLicense}>

                            </ImgUpload>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"其他资质"}
                        hasFeedback
                    >
                        {getFieldDecorator('company.qese', {
                            initialValue: company.qese,
                        })(
                            <Input placeholder="其他资质"/>
                        )}
                    </FormItem>
                        <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"资质编号"}
                        hasFeedback
                    >
                        {getFieldDecorator('company.qeseCode', {
                            initialValue: company.qeseCode,
                        })(
                            <Input placeholder="资质编号"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout} labelCol={{sm: {span: 6}}}
                        label="其他资质扫描件"
                    >
                        {getFieldDecorator('company.qeseFile', {
                            valuePropName: 'fileList',
                            // getValueFromEvent: this.normFile,
                        })(
                            <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={company.qeseFile}>
                            </ImgUpload>
                        )}
                    </FormItem>
                    </div>
                    {/*经营企业基本信息*/}
                    {/*生产企业基本信息 begin*/}
                    <section className="markdown">
                        <h3>生产企业基本信息</h3>
                    </section>

                    {getFieldDecorator('factory.id', {
                        initialValue: factory?factory.id:"",
                    })(
                        <Input type="hidden"/>
                    )}
                     <FormItem 
                        {...formItemLayout}
                        label="企业名称或企业信用代码"
                        hasFeedback
                    >
                        {getFieldDecorator('factory.name1', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业名称或企业信用代码",
                            }],
                            initialValue: factory.name,
                        })(
                            <Input placeholder="企业名称或企业信用代码"/>
                        )}
                      
                    	 <Button type="primary" htmlType="button" size="large" style={styles}
                                onClick={() => this.handleSubmit_1()}>一键填充++++</Button>
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="企业名称"
                        hasFeedback
                    >
                        {getFieldDecorator('factory.name', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业名称.",
                            }],
                            initialValue: factory.name,
                        })(
                            <Input placeholder="企业名称" />
                        )}
                        
                    </FormItem>
                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="输入身份代码"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.idCode', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入身份代码.",
                                }],
                                initialValue: factory.idCode,
                            })(
                                <Input placeholder="输入身份代码"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="企业类型"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.companyType', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入企业类型.",
                                }],
                                initialValue: factory.companyType,
                            })(
                                <Select placeholder="请选择企业类型">
                                    <Option value="140">有限责任公司</Option>
                                    <Option value="141">股份有限公司</Option>
                                </Select>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"法定代表人"}
                            hasFeedback
                        >
                            {getFieldDecorator('factory.legalPerson', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入法定代表人.",
                                }],
                                initialValue: factory.legalPerson,
                            })(
                                <Input placeholder="法定代表人"/>
                            )}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label="企业住所"
                        hasFeedback
                    >
                        {getFieldDecorator('factory.addr', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入企业住所.",
                            }],
                            initialValue: factory.addr,
                        })(
                            <Input placeholder="企业住所"/>
                        )}
                    </FormItem>

                    <Row><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限自"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.businessBegin', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    message: "请输入营业期限自.",
                                }],
                                initialValue: fBusinessBegin,
                            })(
                                <DatePicker />
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="营业期限至"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.businessEnd', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    message: "请选择经营期限至.",
                                }],
                                initialValue: fBusinessEnd,
                            })(
                                <DatePicker />
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="联系人"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.contacter', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入联系人.",
                                }],
                                initialValue: factory.contacter,
                            })(
                                <Input placeholder="联系人"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"联系电话"}
                            hasFeedback
                        >
                            {getFieldDecorator('factory.phone', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入联系电话.",
                                }],
                                initialValue: factory.phone,
                            })(
                                <Input placeholder="联系电话"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label="电子邮件"
                            hasFeedback
                        >
                            {getFieldDecorator('factory.mail', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入电子邮件.",
                                }],
                                initialValue: factory.mail,
                            })(
                                <Input placeholder="电子邮件"/>
                            )}
                        </FormItem>
                    </Col><Col span={12}>
                        <FormItem
                            {...doubleFormItemLayout}
                            label={"移动电话"}
                            hasFeedback
                        >
                            {getFieldDecorator('factory.mphone', {
                                validateTrigger: ['onChange', 'onBlur'],
                                rules: [{
                                    required: true,
                                    whitespace: false,
                                    message: "请输入移动电话.",
                                }],
                                initialValue: factory.mphone,
                            })(
                                <Input placeholder="移动电话"/>
                            )}
                        </FormItem>
                    </Col></Row>
                    <FormItem
                        {...formItemLayout}
                        label={"经营范围"}
                        hasFeedback
                    >
                        {getFieldDecorator('factory.businessScope', {
                            validateTrigger: ['onChange', 'onBlur'],
                            rules: [{
                                required: true,
                                whitespace: false,
                                message: "请输入经营范围.",
                            }],
                            initialValue: factory.businessScope,
                        })(
                            <Input placeholder="经营范围"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        label="营业执照副本扫描件"
                    >
                        {getFieldDecorator('factory.businessLicense', {
                            valuePropName: 'fileList',
                            // getValueFromEvent: this.normFile,
                        })(
                            <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={factory.businessLicense}>

                            </ImgUpload>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"其他资质"}
                        hasFeedback
                    >
                        {getFieldDecorator('factory.qese', {
                            initialValue: factory.qese,
                        })(
                            <Input placeholder="其他资质"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout}
                        labelCol={{sm: {span: 5}}}
                        label={"资质编号"}
                        hasFeedback
                    >
                        {getFieldDecorator('factory.qeseCode', {
                            initialValue: factory.qeseCode,
                        })(
                            <Input placeholder="资质编号"/>
                        )}
                    </FormItem>
                    <FormItem
                        {...formItemLayout} labelCol={{sm: {span: 6}}}
                        label="其他资质扫描件"
                    >
                        {getFieldDecorator('factory.qeseFile', {
                            valuePropName: 'fileList',
                            // getValueFromEvent: this.normFile,
                        })(
                            <ImgUpload name="uploadFile" action="/incc/file/upload/1/1" initialValue={factory.qeseFile}>
                            </ImgUpload>
                        )}
                    </FormItem>
                    {/*生产企业基本信息*/}
                    <FormItem {...tailFormItemLayout}>
                        <Button type="primary" htmlType="button" size="large"
                                onClick={() => this.handleSubmit()}>确定</Button>

                        <Button type="primary" htmlType="reset" size="large" onClick={() => this.reset()}
                                style={{marginLeft: 8}}>重置</Button>
                    </FormItem>

                </Form>

            </SubPage>

        )
    }
}
;
CommodityForm = createForm()(CommodityForm);
export default connect(
    state => {
        let {selectedCommodity, specialItemList, otherQualificationList, industryAndCategory,company} = state;
        return {
            selectedCommodity, specialItemList, otherQualificationList, industryAndCategory,company
        }
    }
)(CommodityForm);