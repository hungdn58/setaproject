<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Item Reply'), ['action' => 'edit', $itemReply->itemID]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Item Reply'), ['action' => 'delete', $itemReply->itemID], ['confirm' => __('Are you sure you want to delete # {0}?', $itemReply->itemID)]) ?> </li>
        <li><?= $this->Html->link(__('List Item Replies'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Item Reply'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="itemReplies view large-9 medium-8 columns content">
    <h3><?= h($itemReply->itemID) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Comments') ?></th>
            <td><?= h($itemReply->comments) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($itemReply->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($itemReply->updateDate) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= h($itemReply->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('ItemID') ?></th>
            <td><?= $this->Number->format($itemReply->itemID) ?></td>
        </tr>
        <tr>
            <th><?= __('WriteUserID') ?></th>
            <td><?= $this->Number->format($itemReply->writeUserID) ?></td>
        </tr>
    </table>
</div>
