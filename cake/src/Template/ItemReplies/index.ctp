<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('New Item Reply'), ['action' => 'add']) ?></li>
    </ul>
</nav>
<div class="itemReplies index large-9 medium-8 columns content">
    <h3><?= __('Item Replies') ?></h3>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th><?= $this->Paginator->sort('itemID') ?></th>
                <th><?= $this->Paginator->sort('comments') ?></th>
                <th><?= $this->Paginator->sort('writeUserID') ?></th>
                <th><?= $this->Paginator->sort('createDate') ?></th>
                <th><?= $this->Paginator->sort('updateDate') ?></th>
                <th><?= $this->Paginator->sort('delFlg') ?></th>
                <th class="actions"><?= __('Actions') ?></th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($itemReplies as $itemReply): ?>
            <tr>
                <td><?= $this->Number->format($itemReply->itemID) ?></td>
                <td><?= h($itemReply->comments) ?></td>
                <td><?= $this->Number->format($itemReply->writeUserID) ?></td>
                <td><?= h($itemReply->createDate) ?></td>
                <td><?= h($itemReply->updateDate) ?></td>
                <td><?= h($itemReply->delFlg) ?></td>
                <td class="actions">
                    <?= $this->Html->link(__('View'), ['action' => 'view', $itemReply->itemID]) ?>
                    <?= $this->Html->link(__('Edit'), ['action' => 'edit', $itemReply->itemID]) ?>
                    <?= $this->Form->postLink(__('Delete'), ['action' => 'delete', $itemReply->itemID], ['confirm' => __('Are you sure you want to delete # {0}?', $itemReply->itemID)]) ?>
                </td>
            </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    <div class="paginator">
        <ul class="pagination">
            <?= $this->Paginator->prev('< ' . __('previous')) ?>
            <?= $this->Paginator->numbers() ?>
            <?= $this->Paginator->next(__('next') . ' >') ?>
        </ul>
        <p><?= $this->Paginator->counter() ?></p>
    </div>
</div>
