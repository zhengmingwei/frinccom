import React from 'react';
import SubPage from 'modules/common/SubPage';
import {refreshCommodityList, commodityCriteriaChanged} from 'actions/CommodityActions';
import {connect} from 'react-redux';
import {Icon, Table, Row, Col, Button} from 'antd';
import CommodityList from './CommodityList';
import CommoditySearch from './CommoditySearch';

/**
 * Commodity: zyj
 * Date: 16/7/19.
 * Time: 上午10:26.
 */
class CommodityManager extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        console.log(this.props.params.id);
        const statusId = this.props.params.id;
        this.props.dispatch(commodityCriteriaChanged({status:statusId}));
        this.props.dispatch(refreshCommodityList({status:statusId}));
    }

    shouldComponentUpdate(nextProps, nextState) {
        const statusId = nextProps.params.id;
        const oldStatusId = this.props.params.id;

        if (statusId != oldStatusId){
            console.log("nextPropsnextProps+++++",nextProps)
            this.props.dispatch(commodityCriteriaChanged({status:statusId}));
            this.props.dispatch(refreshCommodityList({status:statusId}));
            return false;
        }
        return true;
    }

    view(id){
        this.history.push('/manager/commodity/audit/detail/'+ id);
    }

    edit(id){
        this.history.push('/manager/commodity/form/'+ id);
    }

    render() {

        return (
            <div>
                <SubPage breadcrumb="/后台管理/认证管理中心/企业认证信息查询">
                    <Row gutter={16} className="bottom-space">
                        <Col>
                            <CommoditySearch {...this.props}/>
                        </Col>
                    </Row>
                    <Row gutter={16}>
                        <Col>
                            <CommodityList {...this.props} onEdit={this.edit} onView={this.view}/>
                        </Col>
                    </Row>
                </SubPage>
            </div>
        )
    }
};

export default connect(
    state => {
        let {commodityList, commodityStatus, commodityCriteria,currentUser} = state;
        return {
            commodityList, commodityStatus, commodityCriteria,currentUser
        }
    }
)(CommodityManager);