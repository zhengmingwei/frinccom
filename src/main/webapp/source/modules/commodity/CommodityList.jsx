import React from 'react';
import {Icon, Table, Modal, Row, Col} from 'antd';
import {refreshCommodityList, changeCommodityStatus,delCommodity, getCommodity} from 'actions/CommodityActions';
import {showModalDialog} from 'actions/CommonAction';
import DelConfirm from '../common/DelConfirm';
const confirm = Modal.confirm;
/**
 * Commodity: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
export default class CommodityList extends React.Component {
    constructor(props) {
        super(props);
    }

    view(id){
         this.props.onView(id);
    }

    edit(id){
         // this.props.onEdit(id);

        this.props.dispatch(getCommodity(id));
        const _that = this;
        E.addOneTimeEventListener("toCommodity", function (e) {
            _that.props.history.push('/manager/commodity/form/'+ id);
        });

    }

    firstAudit(id){
        console.log(this)
         this.props.history.push('/manager/audit/first/'+ id);
    }

    reAudit(id){
        console.log(this)
         this.props.history.push('/manager/audit/third/'+ id);
    }

    finalAudit(id){
        console.log(this)
         this.props.history.push('/manager/audit/final/'+ id);
    }

    delete(id, commodityCriteria, pageNum){
         this.props.dispatch(delCommodity(id, commodityCriteria, pageNum));
         // this.props.onDelete(id);
    }

render() {
    const {commodityList} = this.props;
    const param = this.props.params.id;
    const {currentUser} = this.props;
    console.log("currentUser",param);
    if(commodityList.list)commodityList.list.map(item=>{item.display=item.status>1?'none':'';});
    const {commodityCriteria} = this.props;
    console.log("commodityList  ==",commodityList);

        const columns = [
            {title: '发布时间', dataIndex: 'createTime', key: 'createTime'},
            {title: '企业名称', dataIndex: 'company.name', key: 'company.name'},
            {title: '产品名称', dataIndex: 'name', key: 'name'},
            {title: '组织机构代码', dataIndex: 'company.idCode', key: 'company.idCode'},
            {title: '所履行业', dataIndex: 'industryPo.value', key: 'industryPo.value'},
            {title: '审核状态', dataIndex: 'statusValue', key: 'statusValue'},
            {
                title: '操作', dataIndex: '', key: 'x', render: (text, record, index) => (
                <span>
                    <a onClick={()=>this.view(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 查看</a>
                    {record.status<2&&param==1?<a  onClick={() => this.edit(record.id)} style={{color: '#2db7f5', cursor: 'pointer'}}> 修改</a>:''}
                    {param==14?<a  onClick={() => this.edit(record.id)} style={{color: '#2db7f5', cursor: 'pointer'}}> 修改</a>:''}
                    {param==12?<a onClick={()=>this.firstAudit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 审核</a>:''}
                    {param==8?<a onClick={()=>this.reAudit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 复审</a>:''}
                    {param==13?<a onClick={()=>this.finalAudit(record.id)} style={{color:'#2db7f5',cursor:'pointer'}}> 终审</a>:''}
                    {record.status<2&&param==1?<DelConfirm onOk = {()=>this.delete(record.id, commodityCriteria, commodityList.pageNum)} style={{color:'#2db7f5',cursor:'pointer'}}/>:''}

                </span>
            ),
            },
        ];

        const pagination = {
            total: commodityList.total,
            pageSize: commodityList.pageSize,
            current: commodityList.pageNum,
            onChange: (page) => {
                this.props.dispatch(refreshCommodityList(commodityCriteria, {"pageNum": page}));
            },
        };

        return (
            <div>
                <Table
                    columns={columns}
                    bordered
                    dataSource={commodityList.list}
                    pagination={pagination}
                    className="table"
                />
            </div>
        )
    }
};
