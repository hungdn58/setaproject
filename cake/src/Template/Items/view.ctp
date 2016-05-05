<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('Edit Item'), ['action' => 'edit', $item->itemID]) ?> </li>
        <li><?= $this->Form->postLink(__('Delete Item'), ['action' => 'delete', $item->itemID], ['confirm' => __('Are you sure you want to delete # {0}?', $item->itemID)]) ?> </li>
        <li><?= $this->Html->link(__('List Items'), ['action' => 'index']) ?> </li>
        <li><?= $this->Html->link(__('New Item'), ['action' => 'add']) ?> </li>
    </ul>
</nav>
<div class="items view large-9 medium-8 columns content">
    <h3><?= h($item->itemID) ?></h3>
    <table class="vertical-table">
        <tr>
            <th><?= __('Contents') ?></th>
            <td><?= h($item->contents) ?></td>
        </tr>
        <tr>
            <th><?= __('ItemID') ?></th>
            <td><?= $this->Number->format($item->itemID) ?></td>
        </tr>
        <tr>
            <th><?= __('UserID') ?></th>
            <td><?= $this->Number->format($item->userID) ?></td>
        </tr>
        <tr>
            <th><?= __('DelFlg') ?></th>
            <td><?= $this->Number->format($item->delFlg) ?></td>
        </tr>
        <tr>
            <th><?= __('CreateDate') ?></th>
            <td><?= h($item->createDate) ?></td>
        </tr>
        <tr>
            <th><?= __('UpdateDate') ?></th>
            <td><?= h($item->updateDate) ?></td>
        </tr>
    </table>
</div>
